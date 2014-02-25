magical-bus
===========

An Android app to view the U of M bus network.

TODO
- [ ] Add API to connect with the network service
- [X] Add Navigation Drawer
- [ ] Add Map View Fragment
- [ ] Add Settings Fragment
- [ ] Fix appearance of... everything
- [ ] Add slick icon
- [ ] Get a Google Maps API V2 Token
- [ ] Determine route list / subway map view mode

###Build / Install
```sh
ant clean
ant debug
adb uninstall edu.umich.magicalbus #remove existing install
adb install bin/MagicalBus-debug.apk
```
