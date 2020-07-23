package id.interconnect.projectlifecyclemanagement

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_invite_member.view.*
import java.lang.ClassCastException

class MakeProjectDialogFragment : DialogFragment() {
    lateinit var name:String
    lateinit var role:String
    private lateinit var myDialogListener:MyDialogListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.dialog_invite_member, null)

        builder.setView(view)
        val roleDropdown = arrayOf("role 1","role 2","role 3")
        val adapter = ArrayAdapter<String>(activity!!, R.layout.dropdown_menu_popup_item, roleDropdown)
        view.dialog_role_dropdown.setAdapter(adapter)
        view.dialog_btn_invite.setOnClickListener{
//            Log.d("CEK INI","CHECKED")
            name = view.dialog_invite_edtname.text.toString()
            role = view.dialog_role_dropdown.text.toString()
            if(name.isEmpty()){
                view.dialog_invite_edtname.setError("name tidak boleh kosong")
            }
            if(role.isEmpty()){
                view.dialog_role_dropdown.setError("Silahkan pilih role")
            }
            if(!name.isEmpty() && !role.isEmpty()){
                myDialogListener.sendText(name,role)
                dismiss()
            }
        }
        view.dialog_invite_btnDelete.setOnClickListener{
            dismiss()
        }
        return builder.create()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            myDialogListener = context as MyDialogListener
        }catch (e : ClassCastException){
            throw ClassCastException(context.toString() + "must implement myDialogListener")
        }
    }

    interface MyDialogListener{
        fun sendText(name:String,role:String)
    }
}