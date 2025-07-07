#!/usr/bin/env bash
set -e

# Reemplazar variables de entorno en el template
envsubst < /etc/agent/grafana-agent.yaml.tmpl > /etc/agent/grafana-agent.yaml

# Lanzar el agent con la config generada
exec /bin/grafana-agent run --config.file=/etc/agent/grafana-agent.yaml
