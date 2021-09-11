/**
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.view.pages.user.Controller', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.user-page-controller',

    onUsersGridSelectionChange: function (model, selected) {
        var record = selected[0];
        this.getViewModel().set('username', record.get('username'));
        this.lookup('user-page-docs-grid-fer').getViewModel().set('userDocsStore', record.getDocs());
    },
});