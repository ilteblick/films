(ns book-list.repositories.protocol)

(defprotocol RepositoryProtocol
  (FindAll [this])
  (FindOne [this id])
  (Insert [this entity])
  (Update [this id entity])
  (Delete [this id]))