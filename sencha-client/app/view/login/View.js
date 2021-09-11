Ext.define('Sencha.classic.view.login.View', {
    extend: 'Ext.window.Window',
    controller: 'login-controller',
    viewModel: 'login-view-model',
    requires: [
        'Sencha.classic.view.login.Controller'
    ],

    /**
     * @event login - событие успешного логина
     */

    title: 'Unknown', // Заголовок окна (Переопределяется в контроллере, методе beforeRender)
    closable: false, // Скрыть кнопку закрытия окна
    bodyPadding: 10, // Оступы тела по краям
    autoShow: true, // Как только создастся, сразу показать

    items: {
        xtype: 'form',
        reference: 'form',
        items: [{
            xtype: 'displayfield',
            bind: {
                value: '{greetingsText:or("Please, enter your username.")}' // Используется Ext.util.Format
            }
        }, {
            xtype: 'displayfield',
            value: 'Please, enter your E-mail.',
            bind: {
                value: '{emailText:or("Please, enter your E-mail.")}' // Используется Ext.util.Format
            }
        }, {
            xtype: 'textfield',
            name: 'username',
            bind: '{username}',
            fieldLabel: 'Username',
            allowBlank: false,
            listeners: {
                specialKey: 'onTextFieldSpecialKey',
                change: 'onUsernameTextFieldChange'  // Если выставлен флаг allowBlank, то криво работает биндинг с ViewModel, приходится принудительно сеттить на изменение текста в поле
            }
        }, {
            xtype: 'textfield',
            name: 'password',
            bind: '{password}',
            fieldLabel: 'Password',
            allowBlank: false,
            inputType: 'password',
            listeners: {
                specialKey: 'onTextFieldSpecialKey',
                change: 'onPasswordTextFieldChange' // Если выставлен флаг allowBlank, то криво работает биндинг с ViewModel, приходится принудительно сеттить на изменение текста в поле
            }
        }, {
            xtype: 'displayfield',
            hideEmptyLabel: false, // Скрывает подпись, тем самым выводит только значение
            value: 'Enter any non-blank password.',
            cls: 'hint'
        }, {
            xtype: 'combobox',
            name: 'organization',
            fieldLabel: 'Organization',
            reference: 'organization',
            queryMode: 'local',
            editable: false,
            forceSelection: true, // Ограничить выбранное значение одним из значений в списке, чтобы пользователь не мог вводить произвольный текст в поле.
            displayField: 'name', // Писать выше displayTpl, либо что-то одно
            valueField: 'id',
            value: 1, // Значение по умолчанию
            matchFieldWidth: false, // Растягивать ширины выпадающего списка по самомому длинному значению
            displayTpl: Ext.create('Ext.XTemplate', '<tpl for="."><tpl>{name}</tpl></tpl>'),
            listConfig: { // Выстройка отображания списка значений
                getInnerTpl: function () {
                    return '<tpl if="code">{code}</tpl><tpl if="name"><tpl if="code">\t-\t</tpl>{name}</tpl>';
                }
            },
            bind: {
                store: '{organizationStore}',
                value: '{defaultOrg}'
            }
        }, {
            xtype: 'checkbox',
            boxLabel: 'E-mail allow',
            reference: 'eMailAllow'
        }, {
            xtype: 'textfield',
            fieldLabel: 'E-mail',
            vtype: 'email',
            bind: {
                value: '{email}',
                disabled: '{!eMailAllow.checked}'
            }
        }],
        buttons: [{
            text: 'Register',
            handler: 'onRegisterClick' // Упрощение для кнопки, для события click
        },
            {
                text: 'Login',
                formBind: true, // Если форма не валидная, кнопку лочиться
                listeners: {
                    click: 'onLoginClick',
                    scope: 'controller' // Не обязательно явно указывать, если указать this, то хендлеры будут искаться текущем представлении
                }
            }]
    }
});