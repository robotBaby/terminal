(ns terminal.core)

(def prices {\A {:unit-price 2 :group-volume 4 :group-price 7}, 
	\B {:unit-price 12}, \C {:unit-price 1.25 :group-volume 6 :group-price 6}, 
	\D {:unit-price 0.15}})

(defn group [g x] (assoc g x (inc(get g x 0)))) 



(defn scan 
	"Scans an item and adds to a collection lazily"
	[coll, item] 
	(lazy-cat coll [item]))


(defn count_price 
	[total [k v]] 
	"Helper function to calculate 
	the price of an item group"
	(let [price (get prices k)] 
		(if (contains? price :group-volume)  
			(+ total (* (get price :group-price) (quot v (get price :group-volume))) (* (rem v (get price :group-volume)) (get price :unit-price)))
			(+ total (* v (get price :unit-price) )) 
			)))



(defn get-total
	"Function that returns the total 
	of a shopping cart given the prices"
	[shopping-cart prices]
	(let [items (reduce scan [] (lazy-seq shopping-cart))
		  groups (reduce group {} items)]
		  (reduce count_price 0 groups)))


(defn -main [cart]
   (println "The total price for this cart is ", (get-total cart prices)))