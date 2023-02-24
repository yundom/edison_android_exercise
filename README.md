# Fact app
This app shows a fact via [fact free api](https://catfact.ninja/fact).

![Screenshot](./fact_app.png)

## Issues
It works, but does not satisfy some requirements.

Please implement TODOs! (If you have time, please also try to do Optional.)

### TODO
- Access data via `Data layer`
- Add local data source using [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
- Add dependency injection by `Hilt`
- Show the `length` from the api response below the `fact` content *only when*
  the `length` is greater than 100
- Show the text "Multiple cats!" when the `fact` contains the word `cats`
  - No context check is rquired, simply finding the workd `cats` is fine
- Make the UI state immutable as much as possible
- Add unit tests

#### Note
It would be great if you give us some feedback about the requirements!

Some of them can be improved, or unnecessary.

### Optional
If we have time, we want to
- (Design) Add the `Top app bar` and update the design 🏰
- (Testing) Add [JUnit5](https://github.com/mannodermaus/android-junit5) and `fake` or `mockk`
- (Gradle) Add a `version catalog` 📗
- (Future growth) `Modularize` the app ✌🏻
- (Future growth) Add `Domain layer` 🚴‍️

## Time
We don't set the time limitation, so you can take your time. Please take it easy ✌🏻

But at the same time, we don't want you to take too long time on this. (don't want to consume your valuable time.)

The below is approximate time for each task group.
- 1~2 hours on `Must` issues
- Another 1~2 hours on `Optional`

Optional requirements are optional, literally.  Please do them only if you can spend your valuable time on this.

## To submit your solution
Please
- Fork this repo ⑂
- Create a pull request in your own repo 📝
  - ❌ Please do not create one in this repo
- Send your the pull request's link to us 🙏
  - We would like to leave some comments for the next step
