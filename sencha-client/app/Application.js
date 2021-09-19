Ext.define('Sencha.classic.Application', {
    extend: 'Ext.app.Application',

    name: 'Sencha.classic',

    requires: [
        'Sencha.classic.view.main.View',
        'Sencha.classic.view.login.View',
        'Sencha.classic.util.AppConstants'
    ],

    launch: function () {
        Ext.getDoc().dom.title = 'Sencha classic demo';
        var token = sessionStorage.getItem(Constants.userToken);
        this.setupMainView(Ext.isEmpty(token));
    },

    setupMainView: function (showLogin) {
        if (showLogin) {
            var loginView = Ext.create('Sencha.classic.view.login.View');
            loginView.on('loginclose', 'onLoginClose', this);
        } else {
            this.setMainView('Sencha.classic.view.main.View');
        }
    },

    onLoginClose: function () {
        window.location.reload();
        // this.setMainView('Sencha.classic.view.main.View');
    },

    quickTips: false,
    platformConfig: {
        desktop: {
            quickTips: true
        }
    },

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
