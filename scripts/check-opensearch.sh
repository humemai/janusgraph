#!/usr/bin/env bash
set -euo pipefail
OS_URL="https://localhost:9200"
AUTH_USER="admin"
AUTH_PASS="myStrongPassword123!"

have_jq() { command -v jq >/dev/null 2>&1; }

echo "--- root"
if have_jq; then curl -sSk -u "$AUTH_USER:$AUTH_PASS" "$OS_URL" | jq .; else curl -sSk -u "$AUTH_USER:$AUTH_PASS" "$OS_URL"; fi

echo "--- health"
if have_jq; then curl -sSk -u "$AUTH_USER:$AUTH_PASS" "$OS_URL/_cluster/health?pretty" | jq .status; else curl -sSk -u "$AUTH_USER:$AUTH_PASS" "$OS_URL/_cluster/health?pretty"; fi

echo "--- indices"; curl -sSk -u "$AUTH_USER:$AUTH_PASS" "$OS_URL/_cat/indices?v" | sed 's/^/  /'

echo "--- sample search (janusgraph_personmixed name:Alice)"
if have_jq; then curl -sSk -u "$AUTH_USER:$AUTH_PASS" "$OS_URL/janusgraph_personmixed/_search?q=name:Alice&pretty" | jq '.hits.hits[0]._source | {name:.name, city:.city, email:.email}'; else curl -sSk -u "$AUTH_USER:$AUTH_PASS" "$OS_URL/janusgraph_personmixed/_search?q=name:Alice&pretty"; fi
