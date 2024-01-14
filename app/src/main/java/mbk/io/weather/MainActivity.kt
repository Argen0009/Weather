package mbk.io.weather

import android.annotation.SuppressLint
import android.net.DnsResolver
import android.net.DnsResolver.Callback
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mbk.io.weather.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener {
            RetrofitService().api.getWeather(binding.cityEd.text.toString())
                .enqueue(object : retrofit2.Callback<WeatherModel> {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<WeatherModel>,
                        response: Response<WeatherModel>,
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                binding.resultTv.text =
                                    "${it.main.temp}"
                            }
                        }
                    }

                    override fun onFailure(call: Call<WeatherModel>, t: Throwable) {

                    }

                })
        }
    }
}