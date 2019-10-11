package com.man.kiwari.view.login


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.man.kiwari.R
import com.man.kiwari.main.InterfaceActivity
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment(), LoginContract.View {

    private var callback: InterfaceActivity? = null
    private lateinit var presenter: LoginPresenter

    fun newInstance(): LoginFragment {
        return LoginFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPresenter()

        btnLogin.setOnClickListener {
            if (validation()){
                presenter.postLogin(edt_email.text.toString(), edt_password.text.toString())
            }
        }
    }

    private fun validation() : Boolean{
        return when{
            edt_email.text.toString().isNotEmpty() &&
                    edt_password.text.toString().isNotEmpty() -> true
            else -> false
        }
    }

    private fun setupPresenter(){
        presenter = LoginPresenter()
        presenter.subscribe(this)
    }

    override fun onSuccessLogin() {
        callback!!.openRoom()
    }

    override fun onFailedLogin(msg : String) {
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
