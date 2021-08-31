audit {
  enabled = true
  sink "consul-sink" {
    type   = "file"
    format = "json"
    path   = "data/audit/audit.json"
    delivery_guarantee = "best-effort"
    rotate_duration = "24h"
    rotate_max_files = 15
    rotate_bytes = 25165824
  }
}