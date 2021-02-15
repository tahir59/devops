consul {
  address = "127.0.0.1:8500"

  retry {
    enabled = true
    attempts = 12
    backoff = "250ms"
    max_backoff = "1m"
  }

}

vault {
  address = "http://127.0.0.1:8200"
  token = "xxxxxxxxxx"
  renew_token = false
}

template {
  source = "/tmp/hello-world-consul.ctmpl"
  destination = "/tmp/hello-world-consul.txt"
}

template {
  source = "/tmp/hello-world-vault.ctmpl"
  destination = "/tmp/hello-world-vault.txt"
}