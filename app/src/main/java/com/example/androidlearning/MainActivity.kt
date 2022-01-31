package com.example.androidlearning

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidlearning.model.Person
import com.example.androidlearning.model.STATUS
import com.example.androidlearning.utils.isMajor
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var personToTest : Person
    private var feedbackString : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        personToTest = Person()

        initView()
    }

    private fun initView() {
        initFirstNameEditText()
        initLastNameEditText()
        initAgeEditText()
        initStatusSpinner()
        initVerifyButton()

        initResultTextView()
    }

    private fun initFirstNameEditText(){
        edit_firstname.hint = "Prénom"
    }

    private fun initLastNameEditText(){
        edit_lastname.hint = "Nom de famille"
    }

    private fun initAgeEditText(){
        edit_age.hint = "Age"
    }

    private fun initStatusSpinner(){
        spin_status.adapter = ArrayAdapter<STATUS>(this, android.R.layout.simple_list_item_1, STATUS.values())
    }

    private fun initVerifyButton(){
        btn_verify.text = "Lancer la vérification"
        manageVerifyButtonClicked()
    }

    private fun manageVerifyButtonClicked(){
        btn_verify.setOnClickListener {
            fillPerson()
            showFeedback()
        }
    }

    private fun initResultTextView(){
        tv_result.text = "Ici s'affichera si la personne peux rentrer ou non"
    }

    private fun fillPerson(){
        personToTest.firstname = edit_firstname.text.toString()
        personToTest.lastname = edit_lastname.text.toString()

        if(edit_age.text.toString().isNotEmpty()){
            personToTest.age = edit_age.text.toString().toInt()
        }

        personToTest.status = spin_status.selectedItem as STATUS

        personToTest.isAuthorized = verifyThePerson()
    }
    private fun verifyThePerson() : Boolean{
        if(personToTest.isMajor()){
            feedbackString += "La personne est majeur \n"
        } else {
            feedbackString += "La personne est mineur \n"
            return false
        }

        when(personToTest.status){
            STATUS.PROFESSOR -> feedbackString += "La personne est un professeur \n"
            STATUS.STUDENT -> feedbackString += "La personne est un étudiant \n"
            else -> {
                feedbackString += "La personne est d'un status inconnue \n"
                return false
            }
        }

        return true
    }


    private fun showFeedback(){

        if(personToTest.isAuthorized){
            layout_principal.background = ContextCompat.getDrawable(this, R.color.colorAuthorized)
            feedbackString += "La personne est donc autorisé à rentré"
        }else{
            layout_principal.background = ContextCompat.getDrawable(this, R.color.colorRefused)
            feedbackString += "La personne n'est donc pas autorisé à rentré"
        }

        tv_result.text = feedbackString
        feedbackString = ""
    }





}