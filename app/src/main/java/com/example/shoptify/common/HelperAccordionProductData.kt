package com.example.shoptify.common

data class AccordionDataModel(
  val title: String,
  val amount: Int = 0
)

data class AccordionListDataModel(
  val title: String,
  val isCollapsed: Boolean = false,
  var data: MutableList<AccordionDataModel> = mutableListOf()
)

class HelperAccordionProductData {
  companion object {
    private var instance: MutableList<AccordionListDataModel>? = null

    fun getInstance(): MutableList<AccordionListDataModel> {
      if (instance == null) {
        initInstance()
      }

      return instance!!
    }

    private fun initInstance() {
      instance = mutableListOf(
        AccordionListDataModel("categories"),
        AccordionListDataModel("vendors")
      )
    }
  }
}