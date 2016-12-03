(ns book-list.views.contents
  (:use [hiccup.form]
        [hiccup.element :only (link-to)]))

(defn index []
  [:div
   [:nav {:class "navbar navbar-default navbar-inverse" :role "navigation"}
    [:div {:class "container"}
     [:div {:class "collapse navbar-collapse" :id "bs-example-navbar-collapse-1"}
      [:form {:class "navbar-form navbar-left" :role "search"}
       [:div {:class "form-group"}
        [:input {:type "text" :class "form-control" :placeholder "Start type book title..."}]
        ]
       [:button {:type "submit" :class "btn btn-default mgn-lft"} "Search"]
       ]
      [:ul {:class "nav navbar-nav navbar-right"}
       [:li {:class "dropdown"}
        [:a {:href "#" :class "dropdown-toggle" :data-toggle "dropdown"} [:b "Login"] [:span {:class "caret"}]]
        [:ul {:id "login-dp" :class "dropdown-menu"}
         [:li
          [:div {:class "row"}
           [:div {:class "col-md-12"}
            [:form {:class "form" :role "form" :method "post" :action "login" :accept-charset "UTF-8"}
             [:div {:class "form-group"}
              [:label {:class "sr-only" :for "myInput_1"} "Email address"]
              [:input {:type "text" :class "form-control" :name "username" :id "myInput_1" :placeholder "Email address"}]
              ]
             [:div {:class "form-group"}
              [:label {:class "sr-only" :for "myInput_2"} "Password"]
              [:input {:type "password" :class "form-control" :name "password" :id "myInput_2" :placeholder "Password"}]
              [:div {:class "help-block text-right"} [:a {:href "password-recovery"} "Forget the password ?"]]
              ]
             [:div {:class "form-group"}
              [:button {:type "submit" :class "btn btn-primary btn-block"} "Sign in"]
              ]
             ]
            ]
           [:div {:class "bottom text-center"}
            "New here ?" [:a {:href "registration"} [:b {:class "mgn-lft"} "Join us"]]
            ]
           ]
          ]
         ]
        ]
       ]
      ]
     ]
    ]
  [:div {:class "container"}
   [:div {:class "row"}
    [:div {:class "col-md-8 col-md-offset-2"}
     [:div {:class "title_block"}
      [:h2 "Welcome to your favorite Library!"]
      ]
     ]
    ]
   ]
   [:section {:class "top_book"}
    [:div {:class "container"}
     [:div {:class "row"}
      [:div {:class "title_block"}
       [:h2 {:class "mgn-top"} "4 most popular books:"]
       ]
      [:div {:class "col-md-3 col-sm-6"}
       [:div {:class "book_item"}
        [:img {:src "../images/vstrecha.png" :class "img-responsive"}]
        [:h4 "До встречи с тобой."]
        [:span "Мойес Джоджо"] [:br]
        [:p "Страшная авария превратила успешного бизнесмена в инвалида. Душевная близость с молодой сиделкой может стать для него спасением или новой трагедией."]
        [:div {:class "rating"}
         [:span "Total rating:"] [:p {:class "likes"} "5.0"] [:br]
         ]
        [:div {:class "form-group"}
         [:a {:href "profile" :class "btn btn-primary"} "Add book"]
         ]
        ]
       ]
      [:div {:class "col-md-3 col-sm-6"}
       [:div {:class "book_item"}
        [:img {:src "../images/ispoved.png" :class "img-responsive"}]
        [:h4 "Я исповедуюсь."]
        [:span "Кабре Жауме"] [:br]
        [:p "Мировой бестселлер. Современный мудрец спешит поведать о вечном и о преходящем, \"прежде чем незримая метла одно за другим сметет из его памяти все события\"."]
        [:div {:class "rating"}
         [:span "Total rating:"] [:p {:class "likes"} "5.0"] [:br]
         ]
        [:div {:class "form-group"}
         [:a {:href "profile" :class "btn btn-primary"} "Add book"]
         ]
        ]
       ]
      [:div {:class "col-md-3 col-sm-6"}
       [:div {:class "book_item"}
        [:img {:src "../images/dragon.png" :class "img-responsive"}]
        [:h4 "Девушка с тату дракона."]
        [:span "Ларссон Стиг"] [:br]
        [:p "Утративший последнюю надежду, влиятельный и известный промышленный магнат поручает поиск своей  племянницы  журналисту Микаэлю Блумквисту, рассчитывая на то, что ему удастся..."]
        [:div {:class "rating"}
         [:span "Total rating:"] [:p {:class "likes"} "5.0"] [:br]
         ]
        [:div {:class "form-group"}
         [:a {:href "profile" :class "btn btn-primary"} "Add book"]
         ]
        ]
       ]
      [:div {:class "col-md-3 col-sm-6"}
       [:div {:class "book_item"}
        [:img {:src "../images/farengeit.png" :class "img-responsive"}]
        [:h4 "451 градус по Фаренгейту."]
        [:span "Брэдбери Рэй"] [:br]
        [:p "Бумага возгорается при температуре 451 градус по Фаренгейту. А на бумаге написаны книги... Фантастический роман, рассказывающий о тоталитарном обществе, котрое сжигает литературное наследие..."]
        [:div {:class "rating"}
         [:span "Total rating:"] [:p {:class "likes"} "5.0"] [:br]
         ]
        [:div {:class "form-group"}
         [:a {:href "profile" :class "btn btn-primary"} "Add book"]
         ]
        ]
       ]
      ]
     ]
    ]
   [:section {:class "last_comments"}
    [:div {:class "container"}
     [:div {:class "row"}
      [:div {:class "title_block"}
       [:h2 "Last comments:"]
       ]
      [:div {:class "col-md-2"}
       [:div {:class "item_comment"}
        [:h4 "Username"]
        [:span "Date"]
        [:p "Роман \"До встречи с тобой\" очень интересный"]
        ]
       ]
      [:div {:class "col-md-2"}
       [:div {:class "item_comment"}
        [:h4 "Username"]
        [:span "Date"]
        [:p "Роман \"До встречи с тобой\" очень интересный"]
        ]
       ]
      [:div {:class "col-md-2"}
       [:div {:class "item_comment"}
        [:h4 "Username"]
        [:span "Date"]
        [:p "Роман \"До встречи с тобой\" очень интересный"]
        ]
       ]
      [:div {:class "col-md-2"}
       [:div {:class "item_comment"}
        [:h4 "Username"]
        [:span "Date"]
        [:p "Роман \"До встречи с тобой\" очень интересный"]
        ]
       ]
      [:div {:class "col-md-2"}
       [:div {:class "item_comment"}
        [:h4 "Username"]
        [:span "Date"]
        [:p "Роман \"До встречи с тобой\" очень интересный"]
        ]
       ]
      [:div {:class "col-md-2"}
       [:div {:class "item_comment"}
        [:h4 "Username"]
        [:span "Date"]
        [:p "Роман \"До встречи с тобой\" очень интересный"]
        ]
       ]
      ]
     ]
    ]
   [:footer {:class "container"}
    [:div {:class "row"}
     [:div {:class "col-xs-12 col-sm-12 col-md-12 col-lg-12"}
      [:div {:class "modal-footer"}
       "Copyright 2016"
       ]
      ]
     ]
    ]
   ])

(defn registration []
  [:div {:class "container"}
   [:div {:class "row"}
    [:div {:class "col-md-6 col-md-offset-3"}
     [:div {:class "title_block"}
      [:h2 "Registration"]]
     [:form {:class "form" :role "form" :method "post" :action "register" :accept-charset "UTF-8"}
      [:div {:class "form-group"}
       [:input {:type "email" :name "email" :class "form-control" :placeholder "Email address"}]]
      [:div {:class "form-group"}
       [:input {:type "password" :name "password" :class "form-control" :placeholder "Password"}]]
      [:div {:class "form-group"}
       [:input {:type "password" :name "confirm_password" :class "form-control" :placeholder "Retype Password"}]]
      [:div {:class "form-group"}
       [:input {:type "text" :name "username" :class "form-control" :placeholder "Enter your username"}]]
      [:div {:class "btn-group"}
       [:a {:href "#" :type "submit" :class "btn btn-success"} "Back to authorization"]]
      [:div {:class "btn-group"}
       [:button {:type "submit" :class "btn btn-success mgn-lft"} "Sign in"]]
      ]
     ]
    ]
   ])

(defn authorization []
  [:div {:class "container"}
   [:div {:class "row"}
    [:div {:class "col-md-6 col-md-offset-3"}
     [:div {:class "title_block"}
      [:h2 "Authorization"]]
     [:form {:class "form" :role "form" :method "post" :action "authorization" :accept-charset "UTF-8"}
      [:div {:class "form-group"}
       [:input {:type "text" :class "form-control" :name "username" :placeholder "Email address"}]]
      [:div {:class "form-group"}
       [:input {:type "password" :class "form-control" :name "password" :placeholder "Password"}]]
      [:div {:class "form-group"}
       [:div {:class "help-block text-right"} [:a {:href "#" :id "link-color"} "Foreget the password ?"]]]
      [:div {:class "btn-group"}
       [:button {:type "submit" :class "btn btn-success"} "Log in"]]
      ]
     ]
    ]
   ])

(defn password-recovery []
  [:div {:class "container"}
   [:div {:class "row"}
    [:div {:class "col-md-6 col-md-offset-3"}
     [:div {:class "title_block"}
      [:h2 "Repair your Password ?"]]
     [:form {:class "form" :role "form" :method "post" :action "login" :accept-charset "UTF-8"}
      [:div {:class "form-group"}
       [:input {:type "email" :class "form-control" :placeholder "Email address"}]]
      [:div {:class "btn-group"}
       [:a {:href "#" :type "submit" :class "btn btn-success"} "Repair"]]
      ]
     ]
    ]
   ]
  )

(defn settings []
  [:div {:class "container"}
   [:div {:class "row"}
    [:div {:class "col-md-6 col-md-offset-3"}
     [:div {:class "title_block"}
      [:h2 "Edit profile"]]
     [:form {:class "form" :role "form" :method "post" :action "login" :accept-charset "UTF-8"}
      [:div {:class "form-group"}
       [:input {:type "password" :class "form-control" :placeholder "Type your old password"}]]
      [:div {:class "form-group"}
       [:input {:type "password" :class "form-control" :placeholder "Type your new password"}]]
      [:div {:class "form-group"}
       [:input {:type "password" :class "form-control" :placeholder "Confirm your new password"}]]
      [:div {:class "form-group"}
       [:input {:type "text" :class "form-control" :placeholder "Edit your username"}]]
      [:div {:class "btn-group"}
       [:a {:href "#" :type "submit" :class "btn btn-success"} "Save"]]
      ]
     ]
    ]
   ]
  )

(defn book-description []
  [:section {:class "top_book"}
   [:div {:class "container"}
    [:div {:class "row"}
     [:div {:class "title_block"}]
     [:div {:class "col-md-12 col-sm-12"}
      [:div {:class "book_item"}
       [:img {:src "../images/vstrecha.png"}]
       [:h4 "До встречи с тобой."]
       [:span "Мойес Джоджо"] [:br]
       [:p "Эта история о молодом человеке (Уилле), который жил в достатке, успехе и любви, но нечастный случай резко меняет его жизнь. Теперь он обречен не жить, а просто существовать в своем инвалидном кресле. Но однажды в его жизни появляется обычная провинциальная девушка (Луиза), которая пытается кардинально изменить его жизнь, вернее его отношение к жизни, она пытается заставить его принять и полюбить жизнь такой какая она есть!\n                        Луизе 26 лет, – у нее не лучший бойфренд, нет образования, перспективной работы.\n                        Но знакомство с Уиллом помогает ей начать жить по другому, .... по-настоящему.\n                        \"Просто живи хорошо. Просто живи. С любовью, Уилл\"\n                        Читая книгу, понимаешь насколько много тебе дала жизнь и как нужно её прожить, чтобы взять от неё всё! Надо жить и ценить каждый миг, любить близких и чаще говорить им об этом. Надо просто любить жизнь. Надо просто жить...\n                        Книга \"До встречи с тобой\" Джоджо Мойес - одна из моих любимых книг! Я давно так не сопереживала героям, здесь я и смеялась, и плакала. Это действительно история о \"маленькой жизни и больших мечтаниях\"!\n "]
       [:div {:class "rating"}
        [:span "Total rating:"] [:p {:class "likes"} "5.0"] [:br]
        [:span "Count page:"] [:p {:class "likes"} "450"] [:br]
        [:span "Year of release:"] [:p {:class "likes"} "1985"] [:br]
        ]
       [:div {:class "form-group"}
        [:a {:href "#" :class "btn btn-primary"} "Back to index"]
        [:a {:href "#" :class "btn btn-primary mgn-lft"} "Add book"]
        ]
       [:div {:class "title_block"}
        [:h2 "Last comments about this book:"]]
       [:div {:class "col-md-4"}
        [:div {:class "item_comment"}
         [:h4 "Username"]
         [:span "Date"] [:br]
         [:p "Comment"]
         ]
        ]
       [:div {:class "col-md-4"}
        [:div {:class "item_comment"}
         [:h4 "Username"]
         [:span "Date"] [:br]
         [:p "Comment"]
         ]
        ]
       [:div {:class "col-md-4"}
        [:div {:class "item_comment"}
         [:h4 "Username"]
         [:span "Date"] [:br]
         [:p "Comment"]
         ]
        ]
       [:div {:class "col-md-4"}
        [:div {:class "item_comment"}
         [:h4 "Username"]
         [:span "Date"] [:br]
         [:p "Comment"]
         ]
        ]
       [:div {:class "col-md-4"}
        [:div {:class "item_comment"}
         [:h4 "Username"]
         [:span "Date"] [:br]
         [:p "Comment"]
         ]
        ]
       ]
      ]
     ]
    ]
   ]
  )

(defn book-add []
  [:div
   [:nav {:class "navbar navbar-default navbar-inverse" :role "navigation"}
    [:div {:class "container"}
     [:div {:class "collapse navbar-collapse" :id "bs-example-navbar-collapse-1"}
      [:form {:class "navbar-form navbar-left" :role "search" :method "post" :action "login" :accept-charset "UTF-8"}
       [:div {:class "form-group"}
        [:input {:type "text" :class "form-control" :placeholder "Start type book title..."}]]
       [:button {:type "submit" :class "btn btn-default mgn-lft"} "Search"]
       ]
      ]
     ]
    ]
   [:section {:class "top_book"}
    [:div {:class "container"}
     [:div {:class "row"}
      [:div {:class "col-md-3 col-sm-6"}
       [:div {:class "book_item"}
        [:img {:src "../images/vstrecha.png" :class "img-responsive"}]
        [:h4 "До встречи с тобой."]
        [:span "Мойес Джоджо"] [:br]
        [:p "Страшная авария превратила успешного бизнесмена в инвалида. Душевная близость с молодой сиделкой может стать для него спасением или новой трагедией."]
        ]
       [:div {:class "rating"}
        [:span "Total rating:"]
        [:p {:class "likes"} "5.0"] [:br]
        ]
       [:div {:class "form-group"}
        [:a {:href "#" :class "btn btn-primary"} "Add book"]
        ]
       ]
      [:div {:class "col-md-3 col-sm-6"}
       [:div {:class "book_item"}
        [:img {:src "../images/ispoved.png" :class "img-responsive"}]
        [:h4 "Я исповедуюсь."]
        [:span "Кабре Жауме"] [:br]
        [:p "Мировой бестселлер. Современный мудрец спешит поведать о вечном и о преходящем, \"прежде чем незримая метла одно за другим сметет из его памяти все события"]
        ]
       [:div {:class "rating"}
        [:span "Total rating:"]
        [:p {:class "likes"} "5.0"] [:br]
        ]
       [:div {:class "form-group"}
        [:a {:href "#" :class "btn btn-primary"} "Add book"]
        ]
       ]
      [:div {:class "col-md-3 col-sm-6"}
       [:div {:class "book_item"}
        [:img {:src "../images/dragon.png" :class "img-responsive"}]
        [:h4 "Девушка с тату дракона."]
        [:span "Ларссон Стиг"] [:br]
        [:p "Утративший последнюю надежду, влиятельный и известный промышленный магнат поручает поиск своей  племянницы  журналисту Микаэлю Блумквисту, рассчитывая на то, что ему удастся..."]
        ]
       [:div {:class "rating"}
        [:span "Total rating:"]
        [:p {:class "likes"} "5.0"] [:br]
        ]
       [:div {:class "form-group"}
        [:a {:href "#" :class "btn btn-primary"} "Add book"]
        ]
       ]
      [:div {:class "col-md-3 col-sm-6"}
       [:div {:class "book_item"}
        [:img {:src "../images/farengeit.png" :class "img-responsive"}]
        [:h4 "451 градус по Фаренгейту."]
        [:span "Брэдбери Рэй"] [:br]
        [:p "Бумага возгорается при температуре 451 градус по Фаренгейту. А на бумаге написаны книги... Фантастический роман, рассказывающий о тоталитарном обществе, котрое сжигает литературное наследие..."]
        ]
       [:div {:class "rating"}
        [:span "Total rating:"]
        [:p {:class "likes"} "5.0"] [:br]
        ]
       [:div {:class "form-group"}
        [:a {:href "#" :class "btn btn-primary"} "Add book"]
        ]
       ]
      ]
     ]
    ]
   ]
  )

(defn profile []
  [:div
   [:nav {:class "navbar navbar-default navbar-inverse" :role "navigation"}
    [:div {:class "container"}
     [:div {:class "collapse navbar-collapse" :id "bs-example-navbar-collapse-1"}
      [:ul {:class "nav navbar-nav navbar-right"}
       [:li {:class "dropdown"}
        [:a {:href "#" :class "dropdown-toggle" :data-toggle "dropdown"} [:b "Username"] [:span {:class "caret"}]]
        [:ul {:id "login-dp" :class "dropdown-menu"}
         [:li
          [:div {:class "row"}
           [:div {:class "col-md-12"}
            [:form {:class "form" :role "form" :method "post" :action "login" :accept-charset "UTF-8"}
             [:div {:class "form-group"}
              [:a {:href "/settings" :class "btn btn-primary btn-block"} "Edit profile"]
              ]
             [:div {:class "form-group"}
              [:a {:href "/" :class "btn btn-primary btn-block"} "Sign out"]
              ]
             ]
            ]
           ]
          ]
         ]
        ]
       ]
      ]
     ]
    ]
   [:div {:id "exTab2" :class "container"}
    [:ul {:class "nav nav-tabs"}
     [:li {:class "active"}
      [:a {:href "#1" :data-toggle "tab"} "My private books"]
      ]
     [:li
      [:a {:href "#2" :data-toggle "tab"} "Read books"]
      ]
     ]
    [:div {:class "tab-content"}
     [:div {:class "tab-pane active" :id "1"}
      [:h3 "My Books:"]
      [:div {:class "col-md-3 col-sm-6"}
       [:div {:class "book_item"}
        [:img {:src "../images/vstrecha.png" :class "img-responsive"}]
        [:h4 "До встречи с тобой."]
        [:span "Мойес Джоджо"] [:br]
        [:p "Страшная авария превратила успешного бизнесмена в инвалида. Душевная близость с молодой сиделкой может стать для него спасением или новой трагедией."]
        [:div {:class "rating"}
         [:span "Total rating:"] [:p {:class "likes"} "5.0"] [:br]
         [:span "My own rating:"] [:p {:class "dislikes"} "4.0"] [:br]
         [:span "Change status:"
          [:select {:class "form-control"}
           [:option "-"]
           [:option "Переместить в прочитанные"]
           ]
          ]
         ]
        [:div {:class "form-group"}
         [:a {:href "#" :class "btn btn-primary"} "Delete book"]
         ]
        ]
       ]
      ]
     [:div {:class "tab-pane" :id "2"}
      [:h3 "Recent read this book:"]
      [:div {:class "col-md-3 col-sm-6"}
       [:div {:class "book_item"}
        [:img {:src "../images/vstrecha.png" :class "img-responsive"}]
        [:h4 "До встречи с тобой."]
        [:span "Мойес Джоджо"] [:br]
        [:p "Страшная авария превратила успешного бизнесмена в инвалида. Душевная близость с молодой сиделкой может стать для него спасением или новой трагедией."]
        [:div {:class "rating"}
         [:span "Total rating:"] [:p {:class "likes"} "5.0"] [:br]
         [:span "My own rating:"] [:p {:class "dislikes"} "4.0"] [:br]
         [:span "Change status:"
          [:select {:class "form-control"}
           [:option "-"]
           [:option "Переместить в личные"]
           ]
          ]
         ]
        [:div {:class "form-group"}
         [:a {:href "#" :class "btn btn-primary"} "Delete book"]
         [:a {:href "#" :class "btn btn-primary mgn-lft" :data-toggle "modal" :data-target "#myModal"} "Add comment"]
         ]
        ]
       ]
      ]
     ]
    ]

   [:div {:class "modal fade" :id "myModal" :tabindex "-1" :role "dialog" :aria-labelledby "myModalLabel" :aria-hidden "true"}
    [:div {:class "modal-dialog"}
     [:div {:class "modal-content"}
      [:div {:class "modal-header"}
       [:button {:type "button" :class "close" :data-dismiss "modal" :aria-hidden "true"} "&times;"]
       [:h4 {:class "modal-title" :id "myModalLabel"} "Отзыв о книге"]
       ]
      [:div {:class "modal-body"}
       [:div {:class "comment"}
        [:h4 "Вася Пупкин"]
        [:span "16.11.2016"] [:br]
        [:textarea {:placeholder "Enter your comment here..." :class "txt-area" :cols "77" :rows "10"}]
        ]
       ]
      [:div {:class "modal-footer"}
       [:button {:type "button" :class "btn btn-default" :data-dismiss "modal"} "Закрыть"]
       [:button {:type "button" :class "btn btn-primary"} "Сохранить изменения"]
       ]
      ]
     ]
    ]
   ]
  )

(defn not-found []
  [:div {:class "well"}
   [:h1 {:class "info-worning"} "Page Not Found"]
   [:p "There's no requested page. "]
   (link-to {:class "btn btn-primary"} "/" "Take me to Home")])

