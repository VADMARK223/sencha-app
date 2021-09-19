Ext.define('Sencha.classic.view.login.Controller', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login-controller',

    requires: [
        'Sencha.classic.util.AppConstants'
    ],

    loginText: 'Logging in ....',

    // Вызывается перед инициализацией представления. Это вызывается до вызова метода initComponent представления.
    beforeInit: function (view) {
        console.debug('Before init login view controller.', view);
    },

    // Вызывается при инициализации представления. Это вызывается после вызова метода initComponent представления.
    init: function (view) {
        console.debug('Init login view controller.', view);
    },

    // Вызывается перед отрисовкой
    beforeRender: function (view) {
        console.debug('Before render', view);
        view.title = 'Login'; // Переопределяем заголовок окна
    },

    // Вызывается при первом создании экземпляра модели представления для присоединенного представления.
    initViewModel: function (viewModel) {
        console.debug('Init login view model', viewModel);
        var organizationStore = this.getStore('organizationStore'); // Аналогично вызову viewModel.getStore('organizationStore');
        console.debug('organizationStore', organizationStore);
        viewModel.bind('{password}', 'onPasswordChange', this);
    },

    // Вызывается после отрисовки
    afterRender: function (view) {
        console.debug('After render', view);
    },

    // Вызывается при уничтожении контроллера представления.
    destroy: function () {
        console.debug('Destroy login controller.');
        this.callParent(arguments);
    },

    onRegisterClick: function () {
        Ext.toast({
            title: 'Info', // Заголовок всплывающего уведомления
            html: 'Not implemented.', // Сообщение всплывающего уведомления
            align: 'br', // Позиционирование всплывающего уведомления на окне (br - снизу и справа, t - сверху, r - справа, b - снизу, l - слева)
            iconCls: 'toast-icon'
        });
    },

    listen: {
        component: {
            'form': {
                beforerender: 'onFormBeforeRender'
            }
        }
    },

    onFormBeforeRender: function (form) {
        // Авто вход
        if (Constants.allowAutoLogin) {
            this.doAutoLogin(form.getForm());
        }
    },

    onLoginClick: function () {
        this.doLogin();
    },

    onTextFieldSpecialKey: function (field, e) {
        if (e.getKey() === e.ENTER) {
            this.doLogin();
        }
    },

    onUsernameTextFieldChange: function (textfield, newValue) {
        this.getViewModel().setData({username: newValue});
    },

    onPasswordTextFieldChange: function (textfield, newValue) {
        this.getViewModel().setData({password: newValue});
    },

    /**
     * Метод для отладки изменения пароля во ViewModel
     * @param value
     */
    onPasswordChange: function (value) {
        console.debug('Password change', value);
    },

    privates: {
        doLogin: function () {
            console.debug('Do login.');
            var form = this.lookup('form'); // Аналогично вызову this.lookupReference('form')

            if (form.isValid()) {
                this.showPreloader();

                if (!this.loginManager) {
                    this.loginManager = new Sencha.classic.LoginManager(
                        {
                            url: Constants.urlPrefix + '/authenticate'
                        }
                    );
                }

                this.loginManager.login({
                    data: form.getValues(),
                    scope: this,
                    success: 'onLoginSuccess',
                    failure: 'onLoginFailure'
                });
            } else {
                console.error('Form not valid');
            }

            var org = this.lookup('organization');
            var orgSelection = org.getSelection();
            if (Ext.isEmpty(orgSelection)) {
                console.debug('Organization not selected.');
            } else {
                console.debug('Organization selected:', orgSelection.getData().name);
            }
        },

        onLoginSuccess: function () {
            this.hidePreloader();
            this.fireViewEvent('loginclose'); // Аналогично вызову this.getView().fireEvent('loginclose');
            this.closeView(); // Аналогично вызову this.getView().close();
        },

        onLoginFailure: function (responseText) {
            Ext.toast(Ext.isEmpty(responseText) ? 'Server connection error.' : responseText);
            this.hidePreloader();
        },

        showPreloader: function () {
            Ext.getBody().mask(this.loginText); // Позывается спиннер
            this.getView().hide();
        },

        hidePreloader: function () {
            Ext.getBody().unmask(); // Скрываем спиннер
            this.getView().show();
        },
        doAutoLogin: function (form) {
            var passwordField = form.findField('password');
            passwordField.on('change', function () {
                if (form.isValid()) {
                    this.doLogin();
                }
            }, this);
        }
    }
});