package com.example.mylivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mylivedata.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mLiveDataTimerViewModel: MainViewModel
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        mLiveDataTimerViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        subscribe()
    }

    /*
    Pemanggilan metode getElapsedTime dilakukan dengan cara subscribe. Jadi setiap ada perubahan
    dari metode tersebut, maka akan mengubah TextView secara otomatis.
     */
    private fun subscribe() {
        /*
        Jika Anda perhatikan kode di bawah, aLong akan selalu diperbarui secara realtime sesuai dengan
        perubahan yang ada di kelas ViewModel. Namun jika elapsedTimeObserver tidak dipanggil saat
        melakukan observe getElapsedTime() maka nilai aLong juga tidak akan ada perubahan

        Jadi cara mendapatkan value dari sebuah LiveData harus dilakukan dengan cara meng-observe
        LiveData tersebut. Dan proses ini dilakukan secara asynchronous.
         */
        val elapsedTimeObserver = Observer<Long?> { aLong ->
            val newText = this@MainActivity.resources.getString(R.string.seconds, aLong)
            activityMainBinding.timerTextview.text = newText
        }
        mLiveDataTimerViewModel.getElapsedTime().observe(this, elapsedTimeObserver)
    }
}