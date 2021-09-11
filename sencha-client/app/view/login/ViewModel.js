Ext.define('Sencha.classic.view.login.ViewModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.login-view-model',

    requires: [],

    data: {
        username: 'Vadim',
        password: 'a',
        // defaultOrg: 1,
        email: null
    },

    stores: {
        organizationStore: {
            fields: ['id', 'name'],
            data: [
                {id: 1, code: '123', name: 'БФТ'},
                {id: 2, code: '457', name: 'ИТЛ'},
                {id: 3, name: 'Очень длинное название организации'}
            ]
        }
    },

    formulas: {
        greetingsText: {
            bind: '{username}',
            get: function (value) {
                if (Ext.isEmpty(value)) {
                    return;
                }
                return 'Hello, ' + value + "!";
            }
        },
        emailText: function (get) {
            const value = get('email');
            if (Ext.isEmpty(value)) {
                return;
            }
            return 'Your E-mail: ' + value;
        }
    }
});