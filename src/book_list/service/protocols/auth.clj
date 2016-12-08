(ns book-list.service.protocols.auth)

(defprotocol AuthServiceProtocol
  (Registration [this fields])
  (Authorization [this]))