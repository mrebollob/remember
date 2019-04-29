# Remember [![Build Status](https://app.bitrise.io/app/dd2325167d9bc561/status.svg?token=Yqlhjz7kujl4fTOf1tyowA&branch=master)](https://app.bitrise.io/app/dd2325167d9bc561)
Remember is a android app to practice the leitner box study method.
{{Work in progress}}

## Architecture
This repo follow Clean Architecture principles.

## Unit test
- mockito
- kluent

## Android test
These tests check the real behaviour of the app in a real device. With this test you can check how work the app in many devices to found behaviour bugs.
I use kakao to make screen tests easier.

## Continuous Integration‎
Every commit to master branch is build by bitrise to build a new version and publish this in play store beta.
TODO : Every commit to develop branch should past the tests and generate a new version.

## TODO
  - Test the main flow with android test
  - Add mockwebserver for android test 
  - Improve unit tests

## License

    Copyright 2018 Manuel Rebollo Báez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.