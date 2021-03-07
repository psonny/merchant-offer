# merchant-offer
TDD is done with one simple case for each, any additional edge case or fail case can be added, but this is just for a simple  
demonstration.


##Usage:
You can access the api through any REST Client etc:  
To add an offer: localhost:8080/NewOffer?name={String}&price={Integer}&expirationDate=yyyy-mm-ddTHH:mm:ss&valid={boolean}&description={String}  
To query an offer: localhost:8080/Offer/{Integer}  
To cancel an offer: localhost:8080/CancelOffer/{Integer}  


##Assumption:
* Merchants are free to create an expired offer and canceled offer.
* Currency only has one unit.
* One offer is given to multiple clients.
* Merchant can create duplicated offers.
* Merchant can cancel expired offers.