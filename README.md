# THIS PROJECT IS OBSOLETE AND NO LONGER MAINTAINED

# Eve Online Dogma Java Implementation

A clean and built from the ground up Java implementation of the [Eve Online's Dogma calculations](https://github.com/ccpgames/eveonline-third-party-documentation/blob/master/docs/dogma/overview.md), which allows for building fitting tools and the like.

This library is used in [Evanova](https://play.google.com/store/apps/details?id=com.tlabs.android.evanova), the Android application for Eve Online.

###Content

This project is divided in multiple librairies which you can pick-up depending on your needs.

* The [engine](engine) library contains the object model and the engine implementation. You will require this library at all times.
```
compile 'com.tlabs.eve.dogma.engine:0.+'
```


* The [ormlite](ormlite) library contains a JPA compliant implementation of the FittingProvider interface using ORMLite.
```
compile 'com.tlabs.eve.dogma.ormlite:0.+'
```

* The [extra](extra) library contains importers and exporters for the fitting model; it currently supports EFT's XML, EVE's clipboard format and JSON based on EFT's XML.
```
compile 'com.tlabs.eve.dogma.extra:0.+'
```
* The [dogma](dogma) library packages all the above into one and provides examples on how to use the fitting engine. You can use this library as a quick starter but you should not include it in real projects as it only contains tests.
```
compile 'com.tlabs.eve.dogma.dogma:0.+'
```


### Setup
* You need Java 1.7 (JDK 1.8 isn't used for Android reasons).

* Run `gradlew build` on Windows or `./gradle build` on MAC/Linux.

* Install in your repository: `gradlew install`

* Include the dependencies in your Gradle project.


####SDE Setup
The [ormlite](ormlite) project contains a stripped down version of the [SDE data dump](https://developers.eveonline.com/resource/static-data-export). You can however use any database type and format as long as it has the same structure as the original SDE. You will have to add the proper driver runtime in the [ormlite/build.gradle](ormlite/build.gradle) file.

The following tables are required:

* dgmAttributesTypes
* dgmEffects
* dgmExpressions
* dgmOperations (1)
* dgmTraits (2)
* dgmTypeAttributes
* dgmTypeEffects
* eveUnits
* invCategories
* invFlags
* invGroups
* invMarketGroups
* invMetaGroups
* invMetaTypes
* invTraits
* invTypes

(1) The DAO layer will create the `dgmOperations` table from a static JSON file if it does not already exist. You will require write-access to your database for this to work.

(2) The DAO layer will create the `dgmTraits` table from *typeIds.yaml* if it does not already exist. You will require write-access to your database for this to work.

### Usage

####Quickstart
Check the `Example` class in the [dogma](dogma) project. The classes you want to start with are:

* `FittingProvider` is an interface around the SDE relevant data. An implementation is provided as `OrmLiteFittingProvider`.

* `Fitter` which is the object to interact with when fitting a ship. You can obtain instances of a Fitter given a FittingProvider and a ship item to start with using `Fitter.Builder`.

####Usage example

```
//Create a FittingProvider
final FittingProvider provider = new OrmLiteItemProvider("jdbc:sqlserver://localhost:1433;databaseName=ebs_DATADUMP;user=sa;password=admin");

//Create a Fitter builder with the provider
final Fitter.Builder builder = new Fitter.Builder(provider);

//Create a Raven Fitter to work with.
final Fitter raven = builder.build("Raven");

//Fit a Large Shield Extender II
raven.fit("Large Shield Extender II");

//Check things out
final double shieldCapacity = raven.getAttributeValue("shieldCapacity");
Assert.assertEquals(9600d, shieldCapacity, 0.1d);

```

####Android
Care is being taken that this project remains compatible with JDK 1.7 with no weird dependencies or requirements (not even retrolambda - sigh - ).

To use it with Android, you only need to include the [engine](engine) and [ormlite](ormlite) dependencies and use the `OrmLiteFittingProvider` within an OrmLiteSqliteOpenHelper (or use the provider directly). Note that you will need to copy the `sdelite.sqlite` file from the [ormlite](ormlite) project if you are too lazy to provide your own database.

Here's a sample implementation using the OrmLiteSqliteOpenHelper: 
```

final class FittingDatabase extends OrmLiteSqliteOpenHelper implements FittingProvider {
    private static final String DATABASE_NAME = "fitting.db";

    private FittingProvider fitting;

    public FittingDatabase(final Context context) {
        super(context, DATABASE_NAME, null, 1);
        try {
            this.fitting = new OrmLiteFittingProvider(getConnectionSource());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
    }

    @Override
    public Item findItem(long l) {
        return fitting.findItem(l);
    }

    @Override
    public Item findItem(String s) {
        return fitting.findItem(s);
    }
}
```

####Details

The `FittingEngine` class is where all the magic happens. To extend/change the fitting calculations, simply add/change sub-classes of `FittingOperation` and add them to the FittingEngine. 

The `com.tlabs.eve.dogma.model` provides you with a client-side view of all objects involved in a fitting.

You can easily use your own SDE data model and swap the datastore to use with the engine by supplying your own implementation of `FittingProvider`; this interface only requires to build `Item` objects. An example implementation which is compatible with JPA can be found in the [ormlite](ormlite) project.

### Contribution guidelines
Look at the unit tests for a good starting point, and create pull requests as you see fit.

This is a work in progress and as such is requiring some love. 

* Some operations need to be looked at/implemented. 
* The `FittingModel` class needs to be re-thought before adding pilots and skills.
* Add support for pilot's skills.
* Add support for implants.
* Ship modifiers need to be scraped from the Eve cache. I have no idea what that entails. It sounds ugly already.

### To whom do I talk to?
evanova.mobile@gmail.com - Reddit [/u/evanova](http://www.reddit.com/u/evanova) - Evanova Android in-game.

### Thanks

The logic of the engine is based on this [explanation about Dogma](https://github.com/ccpgames/eveonline-third-party-documentation/blob/master/docs/dogma/overview.md) and early work from [Carina](https://gitlab.com/aureolin/carina.git)."# eve-dogma-java" 
