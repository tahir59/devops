storage "raft" {
  path = "/tmp/vault/raft"
  node_id = "raft_node_1"
}

#storage "consul" {
#  address = "127.0.0.1:8500"
#  path    = "/tmp/vault/data"
##  token = "xxxxxxxxxxxxxxxxxxxx"
#}

#storage "file" {
#  path = "/tmp/vault-data"
#}

listener "tcp" {
  address     = "0.0.0.0:8200"
  tls_disable = "true"
#  tls_cert_file = "/etc/vault.d/fullchain.pem"
#  tls_key_file = "/etc/vault.d/privkey.pem"
}

telemetry {
  prometheus_retention_time = "30s"
  disable_hostname = true
}

disable_mlock = true
api_addr = "http://127.0.0.1:8200"
cluster_addr = "https://127.0.0.1:8201"
ui = true