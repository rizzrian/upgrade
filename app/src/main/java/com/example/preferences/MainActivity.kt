package com.example.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private val Unit.root: Any
    get() {}

class ActivityMainBinding {
    companion object {
        fun inflate(inflater: LayoutInflater) {

        }
    }

}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }package com.android.preference

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Toast
    import com.android.preference.databinding.ActivityMainBinding

    class MainActivity : AppCompatActivity() {

        private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)

            binding.btnSave.setOnClickListener{
                saveData()
                Toast.makeText(this, "Data Saved.", Toast.LENGTH_SHORT).show()
            }
            loadData()
        }

        private fun saveData() {
            val pref = getSharedPreferences("pref",0)
            val edit = pref.edit() // 수정 모드
            // 1번째 인자는 키, 2번째 인자는 실제 담아둘 값
            edit.putString("name", binding.etHello.text.toString())
            edit.apply() // 저장완료
        }

        private fun loadData() {
            val pref = getSharedPreferences("pref",0)
            // 1번째 인자는 키, 2번째 인자는 데이터가 존재하지 않을경우의 값
            binding.etHello.setText(pref.getString("name",""))
        }
    }
}