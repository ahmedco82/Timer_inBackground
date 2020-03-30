package com.ahmedco;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

// Define the Worker class:
public class MathWorker extends Worker {

    // Define the parameter keys:
    public static final String KEY_X_ARG = "X";
    public static final String KEY_Y_ARG = "Y";
    public static final String KEY_Z_ARG = "Z";
    // ...and the result key:
    public static final String KEY_RESULT = "result";

    public MathWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        // Fetch the arguments (and specify default values):
        int x = getInputData().getInt(KEY_X_ARG, 0);
        int y = getInputData().getInt(KEY_Y_ARG, 0);
        int z = getInputData().getInt(KEY_Z_ARG, 0);

        // ...do the math...
        int result = myCrazyMathFunction(x, y, z);

        //...set the output, and we're done!
        Data output = new Data.Builder()
                .putInt(KEY_RESULT, result)
                .build();
        return Result.success(output);
    }

    private int myCrazyMathFunction(int x, int y, int z) {

        return (x+y+z);
    }
}