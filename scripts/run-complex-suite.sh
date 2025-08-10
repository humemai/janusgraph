#!/usr/bin/env bash
set -euo pipefail

# Simple orchestrator: cleanup -> schema -> data -> queries -> check OpenSearch
HERE="$(cd "$(dirname "$0")" && pwd)"
GREMLIN=(docker exec -i jce-janusgraph bin/gremlin.sh)

run_groovy(){ local f="$1"; echo "=== RUN $f"; "${GREMLIN[@]}" < "$f"; }

run_groovy "$HERE/01-cleanup.groovy"
run_groovy "$HERE/02-schema-complex.groovy"

# Let indexes register
sleep 5
run_groovy "$HERE/03-data-complex.groovy"

# Give mixed-index a moment to ingest
sleep 5
run_groovy "$HERE/04-queries-advanced.groovy"

# Optional OpenSearch checks
if [[ -x "$HERE/check-opensearch.sh" ]]; then "$HERE/check-opensearch.sh"; fi

echo "=== All done ==="
