(ns book-list.service.protocols.base)

(defprotocol BaseServiceProtocol
  (GetItems [this])
  (GetItem [this id])
  (InsertItem [this newItem])
  (UpdateItem [this updateItem])
  (DeleteItem [this id]))
