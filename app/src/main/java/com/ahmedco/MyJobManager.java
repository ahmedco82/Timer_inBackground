package com.ahmedco;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyJobManager extends Worker {


    public MyJobManager(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

            String name = getInputData().getString("NAME_KEY");
            int age = getInputData().getInt("AGE_KEY", -1);
            String result = name + age;
            Data output = new Data.Builder().putString("RESULT_KEY", result).build();

            return Result.success(output);

    }
}