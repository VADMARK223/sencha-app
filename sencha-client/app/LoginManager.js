Ext.define('Sencha.classic.LoginManager', {
    config: {
        url: null
    },

    constructor: function (config) {
        this.initConfig(config); // Инициализирует поля в config значениями пришедшими в констуктор
    },

    login: function (options) {
        Ext.Ajax.request({
            url: this.getUrl(),
            method: 'POST',
            params: options.data,
            scope: this,
            callback: this.onLoginResponse,
            original: options
        });
    },

    onLoginResponse: function (options, success, response) {
        options = options.original;

        if (success) {
            Ext.callback(options.success, options.scope);
        } else {
            Ext.callback(options.failure, options.scope, [response.responseText]);
        }
    }
});