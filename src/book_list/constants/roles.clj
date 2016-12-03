(ns book-list.constants.roles)

(derive ::admin ::user)

(def roles {:user #{::user} :admin #{::admin}})