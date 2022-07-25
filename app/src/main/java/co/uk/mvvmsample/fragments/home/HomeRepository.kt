package co.uk.mvvmsample.fragments.home

import androidx.databinding.adapters.NumberPickerBindingAdapter.setValue
import androidx.lifecycle.MutableLiveData
import co.uk.mvvmsample.model.HomeData


open class HomeRepository {

    private val TAG = "EmployeeRepository"
    private var employees: ArrayList<HomeData> = ArrayList()
    //private val mutableLiveData// = MutableLiveData<List<HomeData>>()
    fun HomeRepository() {}
    fun getMutableLiveData(): MutableLiveData<List<HomeData>> {

        val mutableLiveData = MutableLiveData<List<HomeData>>()
        employees.clear()
        for (i in 1..8){
            employees.add(HomeData(name = "Dev",mobile = "123676377"))
            mutableLiveData.value = employees
        }
        /*val userDataService = RetrofitClient.getService()
        val call = userDataService.getEmployees()
        call.enqueue(object : Callback<EmployeeDBResponse>() {
            fun onResponse(call: Call<EmployeeDBResponse>, response: Response<EmployeeDBResponse>) {
                val employeeDBResponse = response.body()
                if (employeeDBResponse != null && employeeDBResponse!!.getEmployee() != null) {
                    employees = employeeDBResponse!!.getEmployee()
                    mutableLiveData.setValue(employees)
                }
            }

            fun onFailure(call: Call<EmployeeDBResponse>, t: Throwable) {}
        })*/
        return mutableLiveData
    }


}