FROM grafana/agent:latest

COPY grafana-agent.yaml.tmpl /etc/agent/grafana-agent.yaml.tmpl
COPY entrypoint.sh /entrypoint.sh

RUN chmod +x /entrypoint.sh

CMD ["/entrypoint.sh"]
