package com.man.kiwari.view.register


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.man.kiwari.R
import com.man.kiwari.main.InterfaceActivity
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment(), RegisterContract {

    private var callback: InterfaceActivity? = null
    private lateinit var presenter: RegisterPresenter

    fun newInstance(): RegisterFragment {
        return RegisterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPresenter()

        reg_btnRegis.setOnClickListener {
            if (validationInput()) {
                callback!!.showLoading()
                presenter.postRegisterUser(
                    reg_edt_username.text.toString(),
                    reg_edt_email.text.toString(),
                    reg_edt_password.text.toString()
                )
            }else{
                alert("Please input data")
            }
        }
    }

    private fun validationInput(): Boolean {
        return when {
            reg_edt_username.text.toString().isNotEmpty() &&
                    reg_edt_email.text.toString().isNotEmpty() &&
                    reg_edt_password.text.toString().isNotEmpty() -> true
            else -> false
        }
    }

    private fun setupPresenter() {
        presenter = RegisterPresenter()
        presenter.subscribe(this)
    }

    override fun onSuccessLogin() {
        callback!!.dismissLoading()
        callback!!.operDahsboard()
    }

    override fun onFailedLogin(msg: String) {
        alert(msg)
    }

    private fun alert(msg : String){
        callback!!.dismissLoading()
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as InterfaceActivity
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
        presenter.unsubscribe()
    }

}
