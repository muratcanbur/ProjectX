# ProjectX

Android App using The Movie DB API


## Description

a simple app that contains some basic functionality. It connects to the Movies DB API and fetch current popular TV shows on TMDb.


## Tech Stack
- Dagger 2 - Used to provide dependency injection
- Retrofit 2 - OkHttp3 - request/response API
- Glide - for image loading.
- RxJava 2 - reactive programming paradigm
- LiveData - use LiveData to see UI update with data changes.

## Overview of app arch.
- follow the rules from Architecture guidelines recommended by Google.
- keep Activity only responsible for UI related code 
- ViewModel provides data required by the UI class
- Repository layer provides data to ViewModel classes. (single source of truth)

