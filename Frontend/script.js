/* ─────────────────────────────────────────
   TrainFinder — app.js
   API: GET /search/by-code?source=&destinationCode=
───────────────────────────────────────── */

const API_BASE = 'http://localhost:8080';

// ── Utility: calculate duration between two "HH:MM" times ──
function calcDuration(dep, arr) {
  const [dh, dm] = dep.split(':').map(Number);
  const [ah, am] = arr.split(':').map(Number);
  let mins = (ah * 60 + am) - (dh * 60 + dm);
  if (mins < 0) mins += 1440; // handle overnight
  const h = Math.floor(mins / 60);
  const m = mins % 60;
  return `${h}h${m > 0 ? ' ' + m + 'm' : ''}`;
}

// ── Swap source and destination inputs ──
function swapStations() {
  const src = document.getElementById('sourceInput');
  const dst = document.getElementById('destInput');
  [src.value, dst.value] = [dst.value, src.value];
}

// ── Fill inputs and trigger search from quick-route chips ──
function quickSearch(src, dst) {
  document.getElementById('sourceInput').value = src;
  document.getElementById('destInput').value = dst;
  doSearch();
}

// ── Toggle loading state on the search button ──
function setLoading(on) {
  const btn     = document.getElementById('searchBtn');
  const spinner = document.getElementById('btnSpinner');
  const icon    = document.getElementById('btnIcon');
  btn.disabled          = on;
  spinner.style.display = on ? 'block' : 'none';
  icon.style.display    = on ? 'none'  : 'block';
}

// ── Show exactly one of the result panels, hide the rest ──
function showOnly(id) {
  ['skeletons', 'cardsContainer', 'errorBox', 'noResultsBox'].forEach(el => {
    document.getElementById(el).style.display = 'none';
  });
  if (id) document.getElementById(id).style.display = 'block';
}

// ── Main search handler ──
async function doSearch() {
  const src = document.getElementById('sourceInput').value.trim().toUpperCase();
  const dst = document.getElementById('destInput').value.trim().toUpperCase();

  // Basic validation
  if (!src || !dst) {
    alert('Please enter both source and destination station codes.');
    return;
  }
  if (src === dst) {
    alert('Source and destination cannot be the same.');
    return;
  }

  setLoading(true);

  // Show results area with skeleton loaders
  document.getElementById('resultsSection').style.display = 'block';
  document.getElementById('resultsHeader').style.display  = 'none';
  showOnly('skeletons');

  // Smooth scroll to results
  setTimeout(() => {
    document.getElementById('resultsSection').scrollIntoView({ behavior: 'smooth', block: 'start' });
  }, 100);

  try {
    const url = `${API_BASE}/search/by-code?sourceCode=${src}&destinationCode=${dst}`;
    const res  = await fetch(url);

    if (!res.ok) throw new Error(`HTTP ${res.status} — ${res.statusText}`);

    const data   = await res.json();
    const trains = Array.isArray(data) ? data : (data ? [data] : []);

    // Update results header
    showOnly(null);
    document.getElementById('resultsHeader').style.display = 'flex';
    document.getElementById('resultsTitle').textContent    = trains.length > 0
      ? `${trains.length} Train${trains.length !== 1 ? 's' : ''} Found`
      : 'Search Results';
    document.getElementById('resultsMeta').textContent =
      new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
    document.getElementById('routePill').innerHTML =
      `${src} <span style="opacity:.5;margin:0 6px">→</span> ${dst}`;

    if (trains.length === 0) {
      showOnly('noResultsBox');
    } else {
      renderCards(trains);
      showOnly('cardsContainer');
    }

  } catch (err) {
    showOnly('errorBox');
    document.getElementById('errorDetail').textContent  = err.message;
    document.getElementById('resultsHeader').style.display = 'none';
  }

  setLoading(false);
}

// ── Render train result cards into the DOM ──
function renderCards(trains) {
  const container = document.getElementById('cardsContainer');

  container.innerHTML = trains.map((train, idx) => `
    <div class="train-card" style="animation-delay: ${idx * 0.08}s">

      <div class="card-stripe"></div>

      <div class="card-body">

        <!-- Train meta -->
        <div class="train-meta">
          <div class="train-no-badge">${train.trainNumber}</div>
          <div class="train-name">${train.trainName}</div>
          ${train.scheduleList?.[0] ? `
            <div class="duration-badge">
              ⏱ ${calcDuration(
                train.scheduleList[0].departureTime,
                train.scheduleList[0].arrivalTime
              )}
            </div>` : ''}
        </div>

        <!-- Schedule rows -->
        ${(train.scheduleList || []).map(sch => `
          <div class="journey-row">

            <div class="endpoint">
              <div class="ep-time">${sch.departureTime}</div>
              <div class="ep-station">${sch.source?.stationName || '—'}</div>
              <div class="ep-code">${sch.source?.stationCode || ''}</div>
            </div>

            <div class="rail-connector">
              <div class="rail-track-wrap">
                <div class="node node-src"></div>
                <div class="track-line">
                  <div class="track-train">🚆</div>
                </div>
                <div class="node node-dst"></div>
              </div>
            </div>

            <div class="endpoint right">
              <div class="ep-time">${sch.arrivalTime}</div>
              <div class="ep-station">${sch.destination?.stationName || '—'}</div>
              <div class="ep-code">${sch.destination?.stationCode || ''}</div>
            </div>

          </div>
        `).join('')}

      </div>
    </div>
  `).join('');
}

// ── Allow Enter key to trigger search from either input ──
['sourceInput', 'destInput'].forEach(id => {
  document.getElementById(id).addEventListener('keydown', e => {
    if (e.key === 'Enter') doSearch();
  });
});