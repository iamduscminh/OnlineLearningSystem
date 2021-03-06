/* 
 * Check value of input form in jsp file, use javascript
 * Author Dajtvox
 * @type @jQuery
 * Author Dajvox
 */


var pw_reg_value = $("#password_reg").val();
$.validator.addMethod("checklower", function (value) {
    return /[a-z]/.test(value);
});
$.validator.addMethod("checkupper", function (value) {
    return /[A-Z]/.test(value);
});
$.validator.addMethod("checkdigit", function (value) {
    return /[0-9]/.test(value);
});
$.validator.addMethod("pwcheck", function (value) {
    return /^[A-Za-z0-9\d=!\-@._*]*$/.test(value) && /[a-z]/.test(value) && /\d/.test(value) && /[A-Z]/.test(value);
});
$.validator.addMethod("usernamecheck", function (value) {
    return /^[a-zA-Z0-9]+$/.test(value.trim());
});
$.validator.addMethod("lengthcheck", function (value) {
    return value.trim().length > 5 || value.trim().length < 21;
});
$.validator.addMethod("emailcheck", function (value) {
    return /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/.test(value);
});
$.validator.addMethod("captchacheck", function (value) {
    return /^[a-zA-Z0-9]+$/.test(value);
});
$.validator.addMethod("namecheck", function (value) {
    return /^(?![ .]+$)[a-zA-Z .]*$/.test(value);
});
$.validator.addMethod("dobcheck", function (value) {
    var minDate = Date.parse("01/01/1900");
    var today = new Date();
    var DOB = Date.parse(value);
    if (value === null) {
        return true;
    }
    if ((DOB <= today && DOB >= minDate)) {
        return true;
    }
    return false;
});
$('#editProfile-form').validate({
    rules: {
        displayName: {
            required: true,
            namecheck: true,
            lengthcheck: true
        },
        dob: {
            required: true,
            dobcheck: true
        }
    },
    messages: {
        displayName: {
            namecheck: "Not allow only space or number",
            lengthcheck: "Must from 6 and 20 characters"
        },
        dob: {
            dobcheck: 'Must from 01/01/1900 -> now'
        }
    },
    highlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
    },
    unhighlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
    },
    errorElement: 'div',
    errorClass: 'validate_cus',
    errorPlacement: function (error, element) {
        x = element.length;
        if (element.length) {
            error.insertAfter(element);
        } else {
            error.insertAfter(element);
        }
    }
});
$('#changepw-form').validate({
    rules: {
        newPassword: {
            required: true,
            //pwcheck: true,
            checklower: true,
            checkupper: true,
            checkdigit: true,
            lengthcheck: true
        },
        confirmPassword: {
            equalTo: "#password_new"
        }
    },
    messages: {
        newPassword: {
            pwcheck: "Password is not strong enough",
            checklower: "Need at least 1 lowercase alphabet",
            checkupper: "Need at least 1 uppercase alphabet",
            checkdigit: "Need at least 1 digit",
        }
    },
    highlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(element).closest('.form__group').removeClass('has-success').addClass('has-error');
        $(id_attr).removeClass('valid').addClass('invalid');
    },
    unhighlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(element).closest('.form__group').removeClass('has-error').addClass('has-success');
        $(id_attr).removeClass('invalid').addClass('valid');
    },
    errorElement: 'div',
    errorClass: 'validate_cus',
    errorPlacement: function (error, element) {
        x = element.length;
        if (element.length) {
            error.insertAfter(element);
        } else {
            error.insertAfter(element);
        }
    }

});
$('#verifytoken-form').validate({
    rules: {
        captcha: {
            minlength: 6,
            maxlength: 6,
            required: true,
            captchacheck: true
        }
    },
    messages: {
        captcha: {
            captchacheck: "only contains letters and numbers"
        }
    },
    highlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(id_attr).removeClass('valid').addClass('invalid');
    },
    unhighlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(id_attr).removeClass('invalid').addClass('valid');
    },
    errorElement: 'div',
    errorClass: 'validate_cus',
    errorPlacement: function (error, element) {
        x = element.length;
        if (element.length) {
            error.insertAfter(element);
        } else {
            error.insertAfter(element);
        }
    }

});
$('#forgotPassword-form').validate({
    rules: {
        password: {
            minlength: 6,
            maxlength: 20,
            required: true,
            //pwcheck: true,
            checklower: true,
            checkupper: true,
            checkdigit: true
        },
        confirmPassword: {
            equalTo: "#password_reset"
        }
    },
    messages: {
        password: {
            pwcheck: "Password is not strong enough",
            checklower: "Need at least 1 lowercase alphabet",
            checkupper: "Need at least 1 uppercase alphabet",
            checkdigit: "Need at least 1 digit"
        }
    },
    highlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(element).closest('.form__group').removeClass('has-success').addClass('has-error');
        $(id_attr).removeClass('valid').addClass('invalid');
    },
    unhighlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(element).closest('.form__group').removeClass('has-error').addClass('has-success');
        $(id_attr).removeClass('invalid').addClass('valid');
    },
    errorElement: 'div',
    errorClass: 'validate_cus',
    errorPlacement: function (error, element) {
        x = element.length;
        if (element.length) {
            error.insertAfter(element);
        } else {
            error.insertAfter(element);
        }
    }

});
$('#signup-form').validate({
    rules: {
        password: {
            required: true,
            //pwcheck: true,
            checklower: true,
            checkupper: true,
            checkdigit: true,
            lengthcheck: true
        },
        confirmPassword: {
            equalTo: "#password_reg",
        },
        username: {
            required: true,
            usernamecheck: true,
            lengthcheck: true
        },
        email: {
            required: true,
            emailcheck: true
        }
    },
    messages: {
        password: {
            pwcheck: "Password is not strong enough",
            checklower: "Need at least 1 lowercase alphabet",
            checkupper: "Need at least 1 uppercase alphabet",
            checkdigit: "Need at least 1 digit",
            lengthcheck: "Must from 6 and 20 characters"
        },
        username: {
            usernamecheck: "Must only contains alphabet and number",
            lengthcheck: "Must from 6 and 20 characters"
        },
        email: {
            emailcheck: "Invalid format email"
        }
    },
    highlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(element).closest('.form__group').removeClass('has-success').addClass('has-error');
        $(id_attr).removeClass('valid').addClass('invalid');
    },
    unhighlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(element).closest('.form__group').removeClass('has-error').addClass('has-success');
        $(id_attr).removeClass('invalid').addClass('valid');
    },
    errorElement: 'div',
    errorClass: 'validate_cus',
    errorPlacement: function (error, element) {
        x = element.length;
        if (element.length) {
            error.insertAfter(element);
        } else {
            error.insertAfter(element);
        }
    }

});
$('#submitmail-form').validate({
    rules: {
        email: {
            required: true,
            emailcheck: true
        }
    },
    messages: {
        email: {
            emailcheck: "Invalid format email"
        }
    },
    highlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(element).closest('.form__group').removeClass('has-success').addClass('has-error');
        $(id_attr).removeClass('valid').addClass('invalid');
    },
    unhighlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(element).closest('.form__group').removeClass('has-error').addClass('has-success');
        $(id_attr).removeClass('invalid').addClass('valid');
    },
    errorElement: 'div',
    errorClass: 'validate_cus',
    errorPlacement: function (error, element) {
        x = element.length;
        if (element.length) {
            error.insertAfter(element);
        } else {
            error.insertAfter(element);
        }
    }

});
$('#login-form').validate({
    rules: {
        password: {
            required: true
                    //pwcheck: true,
        },
        username: {
            required: true
        }
    },
    messages: {
        password: {
        },
        username: {
        }
    },
    highlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(element).closest('.form__group').removeClass('has-success').addClass('has-error');
        $(id_attr).removeClass('valid').addClass('invalid');
    },
    unhighlight: function (element) {
        var id_attr = "#" + $(element).attr("id");
        $(element).closest('.form__group').removeClass('has-error').addClass('has-success');
        $(id_attr).removeClass('invalid').addClass('valid');
    },
    errorElement: 'div',
    errorClass: 'validate_cus',
    errorPlacement: function (error, element) {
        x = element.length;
        if (element.length) {
            error.insertAfter(element);
        } else {
            error.insertAfter(element);
        }
    }
});