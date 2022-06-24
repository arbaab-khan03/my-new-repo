package com.example.driverhiringapp.data;

import android.provider.BaseColumns;

public final class UserContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private UserContract() {}
    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class UserEntry implements BaseColumns {

        /** Name of database table for pets */
        public final static String TABLE_NAME = "User";

        /**
         * Unique ID number for the pet (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the pet.
         *
         * Type: TEXT
         */
        public final static String COLUMN_USER_NAME ="username";

        /**
         * Breed of the pet.
         *
         * Type: TEXT
         */
        public final static String COLUMN_USER_PASSWORD = "password";


        public final static String COLUMN_USER_CONFIRM_PASSWORD = "confirm_password";
        /**
         * Gender of the pet.
         *
         * The only possible values are {@link #GENDER_UNKNOWN}, {@link #GENDER_MALE},
         * or {@link #GENDER_FEMALE}.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_USER_MOBILE_NUMBER = "mobile_number";

        /**
         * Weight of the pet.
         *
         * Type: INTEGER
         */
        public final static String COLUMN_USER_EMAIL_ID = "email_id";



        public final static String COLUMN_USER_GENDER = "gender";


        public final static String COLUMN_USER_CITY = "city";


        public final static String COLUMN_USER_STATE = "state";


        public final static String COLUMN_USER_PINCODE = "pincode";




        /**
         * Possible values for the gender of the pet.
         */

        // remember that one BlankEntry represents one table in the database
        // so in the above table we have 9 columns including id column
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }

    public static final class CarEntry implements BaseColumns {

        // It is a car table basically


        public final static String TABLE_NAME = "Cars";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_CAR_BRAND ="car_brand";

        public final static String COLUMN_CAR_MODEL = "model";

        public final static String COLUMN_CAR_REGISTRATION_NUMBER = "registration_number";

        public final static String COLUMN_CAR_CHASIS_NUMBER = "Chasis_number";

        public final static String COLUMN_CAR_YEAR_OF_PURCHASING = "Year_of_purchasing";

        public final static String COLUMN_CAR_TYPE_OF_CAR = "Type_of_car";

        public final static String COLUMN_CAR_TRANSMISSION = "Transmission";

        public final static String COLUMN_CAR_FUEL_TYPE = "Fuel_Type";
    }

    public static final class DriverEntry implements BaseColumns {

        // It is a car table basically


        public final static String TABLE_NAME = "Driver";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_DRIVER_NAME ="name";

        public final static String COLUMN_DRIVER_MOBILE_NUMBER = "mobile_number";

        public final static String COLUMN_DRIVER_EMAIL_ID = "email_id";

        public final static String COLUMN_DRIVER_DATE_OF_BIRTH = "date_of_birth";

        public final static String COLUMN_DRIVER_GENDER = "gender";

        public final static String COLUMN_DRIVER_PRICE_PER_HOUR = "price_per_hour";

        public final static String COLUMN_DRIVER_EXPERIENCE_IN_MONTHS = "experience_in_months";
        // it should be stored as number of months and not less than one month is allowed


        public final static String COLUMN_DRIVER_PINCODE = "pincode";

        public final static String COLUMN_DRIVER_PASSWORD = "password";

        public final static String COLUMN_DRIVER_CONFIRM_PASSWORD = "confirm_password";


    }


}

