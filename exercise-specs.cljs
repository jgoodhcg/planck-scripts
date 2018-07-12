(ns exercise.specs
  (:require [clojure.spec.alpha :as s]))

(s/def ::positive-int (s/and int? #(> % 0)))
(s/def ::s ::positive-int)
(s/def ::r ::positive-int)
(s/def ::w ::positive-int)

(def exercises
  [{:id :pullups
    :description "Overhand shoulder width grip."}
   {:id :band-pulls
    :link "https://youtu.be/JObYtU7Y7ag?t=4m2s"
    :description "Modification on band pulls fro Athlean-X"}
   {:id :dislocates}
   {:id :chinups}
   {:id :dips}
   {:id :pushups}
   {:id :squats}
   {:id :wrist-mobility
    :description "Varying amount of exercises described in link."
    :link "https://www.youtube.com/watch?v=VwQ5E0DeaoQ"}])

(s/def ::id keyword?) ;; TODO should this reference exercises? generate a set from ids?
(s/def ::link string?)
(s/def ::description string?)
(s/def ::exercise (s/keys :req-un [::id]
                          :opt-un [::link ::description]))

(s/def ::session-exercise-set (s/keys :req-un [::s ::r]
                                      :opt-un [::w]))
(s/def ::session-exercise (s/coll-of ::session-exercise-set))

(s/def ::session (s/map-of
                  (fn [k] (some #(= k (:id %)) exercises))
                  ::session-exercise))

(println (s/explain ::session {:band-pulls     [{:s 3 :r 10}]
                               :dislocates     [{:s 1 :r 10}]
                               :wrist-mobility [{:s 1 :r 25}]
                               :pullups        [{:s 1 :r 10}]
                               :chinups        [{:s 1 :r 10}
                                                {:s 1 :r 5}]}))
