FROM grafana/agent:latest

# Copy the configuration file (replace with your actual path)
COPY ./grafana-agent.yaml /etc/agent/agent.yaml

# Set the entrypoint (replace with your desired command)
ENTRYPOINT ["/bin/grafana-agent"]

CMD ["--config.file=/etc/agent/agent.yaml"]

# Expose necessary ports (if applicable)
EXPOSE 12345