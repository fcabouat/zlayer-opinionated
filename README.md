# ZIO's opinionated ZLayer code structuration

### About

This is a toy example demonstrating ZIO's ZLayer functionality.

The code doesn't respect the "module pattern" used in ZIO's code source and uses some alternative code structuration
pattern instead.

Main reasons for departing from "module pattern" are :
1. I find it to be a bit cluttered : I'd rather have seperate files for traits, to grasp at a glance what a service's
interface is all about.
2. I also use scala packages as logical grouping of multiple services and would like to retain that property (no 1
package object per service approach).

### Alternative structuring

    » src/main/scala
    .
    ├── AppEnv.scala                          // App env creation helpers
    ├── Main.scala                            // Main file
    |
    └── mypackage
        ├── impl                              
        │   ├── Service1Live.scala            // Default Impl 1
        |   |
        │   ├── Service2Live.scala            // Default Impl 2, decorating delegate with Logging
        │   └── Service2Delegate.scala        // Default Impl 2, actual service impl
        |
        ├── package.scala                     // Custom types, env helpers
        |
        ├── Service1.scala                    // Trait 1
        └── Service2.scala                    // Trait 2
