Ext.define('Sencha.classic.view.main.Controller', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main-controller',

    requires: [
        'Sencha.classic.util.AppConstants'
    ],

    mixins: [
        'Sencha.classic.mixin.ToastMixin'
    ],

    init: function () {
        this.getViewModel().set('token', sessionStorage.getItem(Constants.userToken));
    },

    onKeyMapSpace: function () {
        Ext.Msg.alert('Info', 'You have pressed space.');
    },

    onSingOutHandler: function () {
        sessionStorage.removeItem(Constants.userToken);
        window.location.reload();
    },

    onClearCacheHandler: function () {
        var self = this;
        Ext.Ajax.request({
            url: Constants.urlPrefix + '/cache/clear',
            method: 'GET',
            success: function (response) {
                self.showToast(response.responseText);
            },
            failure: function (response) {
                self.showToastError(response.responseText);
            }
        });
    },

    onTokenTextfieldClickHandler: function () {
        var textfield = this.lookup('token-textfield-ref');
        textfield.focus();

        try {
            var successful = document.execCommand('copy');
            if (successful) {
                this.showToast('Token copied.');
            }
        } catch (e) {
            console.error('Error', e);
        }
    }
});
