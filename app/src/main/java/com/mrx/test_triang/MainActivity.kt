package com.mrx.test_triang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver
import com.lemmingapex.trilateration.TrilaterationFunction
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer

data class Point(val x: Double, val y: Double)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val points: MutableList<Point> = mutableListOf(Point(0.0, 0.0), Point(10.0, 0.0), Point(0.0, 10.0), Point(10.0, 10.0))
        val distances: MutableList<Double> = mutableListOf(9.21954, 6.70820, 8.06225, 5.0)

        val in_points = points.map { doubleArrayOf(it.x, it.y) }.toTypedArray()
        val in_distances = distances.toDoubleArray()

        val nllss = NonLinearLeastSquaresSolver(TrilaterationFunction(in_points, in_distances), LevenbergMarquardtOptimizer())

        val optimum = nllss.solve()

        val result = optimum.point.toArray()

        val res = Point(x = result[0], y = result[1])

        Log.d("mytag", res.toString())
    }
}