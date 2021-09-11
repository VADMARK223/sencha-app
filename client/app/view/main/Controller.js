Ext.define('Sencha.classic.view.main.Controller', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main-controller',

    onKeyMapSpace: function () {
        Ext.Msg.alert('Info', 'You have pressed space.');
    }
});
