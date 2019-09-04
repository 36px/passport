



export default {

    name: 'CredentialQueryModule',

    namespaced: false,

    state: {

        table: {},

        selected_key: '',

        selected_item: {},

    },

    getters: {

        table(state) {
            return state.table;
        },

        selectedItem(state) {
            return state.selected_item;
        },

        selectedKey(state) {
            return state.selected_key;
        },

    },

    mutations: {},

    actions: {

        selectCredentials(context, param) {
            var account = param.account;
            var domain = param.domain;

            var method = 'post';
            var type = 'Query';
            var id = null;

            var content = {
                query: {
                    account, domain,
                }
            };

            context.dispatch('rest', { method, type, id, content });

        }

    },

}