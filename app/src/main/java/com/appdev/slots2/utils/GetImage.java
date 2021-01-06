package com.appdev.slots2.utils;

import com.appdev.slots2.R;

public abstract class GetImage {
    public static int imageName(String name) {
        int imageRes = 0;

        switch (name) {
            case "0":
                imageRes = R.drawable.ic_cards;
                break;

            case "1":
                imageRes = R.drawable.ic_dices;
                break;

            case "2":
                imageRes = R.drawable.ic_lemon;
                break;

            case "3":
                imageRes = R.drawable.ic_mellon;
                break;

            case "4":
                imageRes = R.drawable.ic_slots;
                break;

        }

        return imageRes;
    }

    public static String name(String name) {

        String imageRes = "";

        switch (name) {
            case "0":
                imageRes = "cards";
                break;

            case "1":
                imageRes = "dices";
                break;

            case "2":
                imageRes = "lemon";
                break;

            case "3":
                imageRes = "mellon";
                break;

            case "4":
                imageRes = "slots";
                break;

        }

        return imageRes;

    }
}
