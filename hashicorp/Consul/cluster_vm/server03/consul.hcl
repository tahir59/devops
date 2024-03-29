node_name = "node03"
advertise_addr = "node03"
bootstrap_expect = 3
bind_addr = "node03"
client_addr = "0.0.0.0"
data_dir = "/tmp/consul"
datacenter = "dc-name"
enable_script_checks = true
encrypt = "xxxxxxxxxx"
leave_on_terminate = true
log_level = "DEBUG"
enable_syslog = true
rejoin_after_leave = true
retry_join = ["node01","node02","node03"]
server = true
#ui = true
#ca_file = "/etc/consul.d/consul-agent-ca.pem"
#cert_file = "/etc/consul.d/dc1-server-consul-0.pem"
#key_file = "/etc/consul.d/dc1-server-consul-0-key.pem"
#verify_incoming = true
#verify_outgoing = true
#verify_server_hostname = true