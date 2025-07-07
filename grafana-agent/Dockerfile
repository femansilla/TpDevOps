FROM grafana/agent:latest

# Copiamos el config del agente
COPY grafana-agent.yaml /etc/agent/agent.yaml

# Dummy HTTP server para que Render lo acepte como "web service"
COPY dummy-server.sh /dummy-server.sh
RUN chmod +x /dummy-server.sh

# Port required by Render
EXPOSE 10000

# Ejecuta el agente + dummy server en paralelo
CMD ["/bin/sh", "-c", "/dummy-server.sh & agent run --config.file=/etc/agent/agent.yaml"]
