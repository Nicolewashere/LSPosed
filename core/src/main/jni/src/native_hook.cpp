/*
 * This file is part of LSPosed.
 *
 * LSPosed is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LSPosed is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LSPosed.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Copyright (C) 2020 EdXposed Contributors
 * Copyright (C) 2021 LSPosed Contributors
 */

#include <dlfcn.h>
#include <string>
#include <vector>
#include <dobby.h>
#include "symbol_cache.h"
#include "logging.h"
#include "native_api.h"
#include "native_hook.h"
#include "art/runtime/hidden_api.h"

namespace lspd {
    static std::atomic_bool installed = false;

    void InstallInlineHooks(const lsplant::HookHandler& handler) {
        if (installed.exchange(true)) [[unlikely]] {
            LOGD("Inline hooks have been installed, skip");
            return;
        }
        LOGD("Start to install inline hooks");
        art::Runtime::Setup(handler);
        art::hidden_api::DisableHiddenApi(handler);
        LOGD("Inline hooks installed");
    }
}  // namespace lspd

