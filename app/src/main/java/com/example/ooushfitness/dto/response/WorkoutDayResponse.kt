package com.example.ooushfitness.dto.response

import com.example.ooushfitness.dto.mapper.ExerciseMapper
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class WorkoutDayResponse {

    @SerializedName("data")
    @Expose
    private var data : List<WorkoutDayResponseData?>? = null

    @SerializedName("size")
    @Expose
    private var size : Int? = null;

    fun getData(): List<WorkoutDayResponseData?>? {
        return this.data;
    }

    fun getSize(): Int? {
        return this.size;
    }

    class WorkoutDayResponseData {
        private var day: String? = null
        private var exerciseDayId: Int? = null
        private var weekday = false
        private var name: String? = null
        private var exercises: List<ExerciseMapper?>? = null

        fun getDay(): String? {
            return day
        }

        fun setDay(day: String?) {
            this.day = day
        }

        fun getExerciseDayId(): Int? {
            return exerciseDayId
        }

        fun setExerciseDayId(exerciseDayId: Int?) {
            this.exerciseDayId = exerciseDayId
        }

        fun isWeekday(): Boolean {
            return weekday
        }

        fun setWeekday(weekday: Boolean) {
            this.weekday = weekday
        }

        fun getName(): String? {
            return name
        }

        fun setName(name: String?) {
            this.name = name
        }

        fun getExercises(): List<ExerciseMapper?>? {
            return exercises
        }

        fun setExercises(exercises: List<ExerciseMapper?>?) {
            this.exercises = exercises
        }
    }

}