/**
 * @author Markitanov Vadim
 * @since 05.09.2021
 */
Ext.define('Sencha.classic.view.pages.personnel.Controller', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.personnel-page-controller',

    onPersonnelGridSelectionChange: function (model, selected) {
        var record = selected[0];
        var vm = this.getViewModel();

        if (!Ext.isEmpty(record)) {
            vm.set('personnelName', record.get('name'));
            vm.set('address', record.get('address'));
        }
    },

    onApplyBtnHandler: function () {
        console.log('AAAAAAAAAAAAAA');
    }
});