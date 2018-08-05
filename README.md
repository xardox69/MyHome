# Home Items Listings

A simple Android application which shows home items listings.


 This app stands on the principles of [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) 
 
 
 It's based on the **MVP**  adding a domain layer between the presentation layer and repositories,
 splitting the app in three layers:
 
 **MVP:** Model View Presenter pattern.
 
 
 **Domain:** Holds all business logic. The domain layer starts with domain package name.
  
 
 **Repository:** Repository pattern.
 
  ![Image](https://raw.githubusercontent.com/wiki/googlesamples/android-architecture/images/mvp-clean.png)
 
 
 **Libraries**
*  [Retrofit2](http://square.github.io/retrofit/) for Rest API communication.
*  [Glide](https://github.com/bumptech/glide) for image loading
*  [Mockito](http://site.mockito.org) for mocking in test 
*  [Dagger](https://github.com/google/dagger) for dependency injection 
*  [Jackson](https://github.com/FasterXML/jackson-core) for pasing json response 
 
 
 
 


# Unit Testing
Unit testing of all the presenter is done  as well as the instrumentation tests.




# Feedback
For feedback and quering, please email at khalid.usman7@gmail.com.
