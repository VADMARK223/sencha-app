/**
 * Представление таблцы пользователей
 * @author Markitanov Vadim
 * @since 04.09.2021
 */
Ext.define('Sencha.classic.view.pages.user.grid.ViewModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.user-page-grid-view-model',

    requires: [
        'Sencha.classic.store.UserStore'
    ],

    stores: {
        usersStore: {
            type: 'user-store'
        }
    }
});