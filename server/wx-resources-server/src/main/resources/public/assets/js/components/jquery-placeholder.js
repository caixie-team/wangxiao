/*!
 * remark (http://getbootstrapadmin.com/remark)
 * Copyright 2016 amazingsurge
 * Licensed under the Themeforest Standard Licenses
 */
$.components.register("placeholder", {
  mode: "init",
  init: function(context) {
    if (!$.fn.placeholder) return;

    $('input, textarea', context).placeholder();
  }
});
